$(function () {
    fetchCastleSvg();
    reloadStatus();
    fetch("/castle-lookout/watchtower-location", {redirect: 'manual'})
        .then(res => res.ok ? res.text() : Promise.reject(res))
        .then(loc => {
            new EventSource(loc + "/register").onmessage = e => onSignalFromWatchtower(e.data);
        })
        .catch((error) => console.error('Please login:', error));
    function onSignalFromWatchtower(data) {
        $('.signal-light').fadeIn(300);
        setTimeout(function(){
            $('.signal-light').fadeOut(300);
        }, 2000);
    }
});


function controlGate(cmd) {
    fetch("/gate/" + cmd, {
        method: 'PUT',
        redirect: 'manual'
    })
        .then(res => {
            if (!res.ok && res.status != 304) {
                error(res.status + " " +  res.statusText, res.url)
            }
        })
        .then((unused) => reloadStatus())
        .catch((e) => error("Gate error", e));
}

function controlFlag(cmd) {
    fetch("/flag/" + cmd, {
        method: 'PUT',
        redirect: 'manual'
    })
        .then(res => {
            if (!res.ok && res.status != 304) {
                error(res.status + " " +  res.statusText, res.url)
            }
        })
        .then((unused) => reloadStatus())
        .catch((e) => error("Flag error", e));
}

function signalWatchTower() {
    $('#castle-light').fadeIn(100);
    setTimeout(function(){
        $('#castle-light').fadeOut(100);
        fetch("/castle-lookout/send-signal?" + new URLSearchParams({msg: window.location.origin}), {
            method: 'POST',
            redirect: 'manual'
        })
            .then(res => {
                if (!res.ok && res.status != 304) {
                    error(res.status + " " + res.statusText, res.url)
                }
            })
            .then((unused) => reloadStatus())
            .catch((e) => error("Flag error", e));
    }, 1000);
}

function reloadStatus() {
    fetch('/castle', {redirect: "manual"})
        .then(res => res.ok ? res.json() : Promise.reject(res))
        .then(json => {
            if (json.gateOpened) {
                $("#gate").fadeOut(300);
            } else {
                $("#gate").fadeIn(300);
            }

            if (json.flagRaised) {
                $("#flag").fadeIn(300);
            } else {
                $("#flag").fadeOut(300);
            }

            fetch("/castle/principal", {redirect: "manual"})
                .then(res => res.ok ? res.json() : Promise.reject(res))
                .then((res) => {
                    $("#label").text(res.fullName);
                    $('button.ctrl').prop('disabled', false);
                })
                .catch((error) => console.error('Please login:', error));
        })
        .catch((e) => $('button.ctrl').prop('disabled', true));
}

function error(title, msg) {
    $('div#error-title').text(title);
    $('div#error-msg').text(msg);
    $('div#cover').show();
    $('div#canvas').addClass('blurred');
}

function login() {
    $(location).attr('href', "/castle/login?redirect=" + encodeURIComponent(window.location));
}

function logout() {
    fetch("/castle/principal", {
        redirect: 'manual'
    })
        .then((res) => {
            if (res.ok) {
                $('button.ctrl').prop('disabled', true);
                $(location).attr('href', "/oidc/logout");
            } else {
                error("Are you logged in?", res.statusText)
            }
        })
        .catch((error) => console.error('Error:', error));
}

function fetchCastleSvg() {
    fetch("/castle.svg")
        .then(response => response.text())
        .then(xml => $("div#svg-wrapper").append($(xml)));

    fetch("/tower.svg")
        .then(response => response.text())
        .then(xml => $("div#tower-svg-wrapper").append($(xml)));
}