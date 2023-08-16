# Helidon LRA example
Set of services demonstrating usage of the 
[MicroProfile Long Running Actions](https://download.eclipse.org/microprofile/microprofile-lra-1.0/microprofile-lra-spec-1.0.html)
in Helidon MP.

## Online cinema booking system
Our hypothetical cinema needs an online reservation system. 
We will split it in the two scalable services:
* [seat-booking-service](/seat-booking-service), 
* [payment-service](/payment-service)

Our services will be completely separated, integrated only through the REST API calls.

Additional services are needed in order to coordinate
LRA transactions and persist booking data: 
* [lra-coordinator-service](/lra-coordinator-service) 
* [booking-db](/booking-db) 

#### Build images
As we work directly with
[minikube docker daemon](https://minikube.sigs.k8s.io/docs/handbook/pushing/#1-pushing-directly-to-the-in-cluster-docker-daemon-docker-env)
all we need to do is build the docker images.
```shell
bash build-minikube.sh;
```
Note that the first build can take few minutes for all the artifacts to download.
Subsequent builds are going to be much faster as the layer with dependencies gets cached.

### Deploy to minikube
Prerequisites:
* Installed and started minikube
* Environment with
  [minikube docker daemon](https://minikube.sigs.k8s.io/docs/handbook/pushing/#1-pushing-directly-to-the-in-cluster-docker-daemon-docker-env) - `eval $(minikube docker-env)`

#### Build images
As we work directly with
[minikube docker daemon](https://minikube.sigs.k8s.io/docs/handbook/pushing/#1-pushing-directly-to-the-in-cluster-docker-daemon-docker-env)
all we need to do is build the docker images.
```shell
bash build-minikube.sh;
```
Note that the first build can take few minutes for all the artifacts to download.
Subsequent builds are going to be much faster as the layer with dependencies is cached.

#### Deploy
```shell
bash deploy-minikube.sh
```
This script recreates the whole namespace, any previous state of the `cinema-reservation` is obliterated.
Deployment is exposed via the NodePort and the URL with port is printed at the end of the output:
```shell
namespace "cinema-reservation" deleted
namespace/cinema-reservation created
Context "minikube" modified.
service/booking-db created
service/lra-coordinator created
service/payment-service created
service/seat-booking-service created
deployment.apps/booking-db created
deployment.apps/lra-coordinator created
deployment.apps/payment-service created
deployment.apps/seat-booking-service created
service/cinema-reservation exposed

Waiting for main service to come up ... pod/seat-booking-service-6765c76d79-s4ssj condition met

Application cinema-reservation is available at http://192.0.2.254:31584
```
