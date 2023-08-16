# 08 - Security sample - TLS

## Setup HTTPS with TLS 1.3 

1. Create your own Certification Authority(CA)
    ```shell
    bash createCA.sh
    ```
2. Create Certificate Sign Request(CSR)
    ```shell
    bash createCSR.sh
    ```
3. Sign Certificate Sign Request(CSR) with your own CA
    ```shell
    bash selfSignCSR.sh
    ```
4. Copy server certificate to artefact resources
    ```shell
    cp server.p12 src/main/resources/
    ```
5. Build and run
    ```shell
    mvn clean package && java -jar ./target/castle-tls.jar
    ```

6. Check castle endpoint

   ```shell
   ~$ curl -k -v https://localhost:8080/castle 2>&1 | grep -A 4 'Server certificate'
   * Server certificate:
   *  subject: C=CZ; ST=Prague; L=Prague; O=Apress; OU=BeginningHelidon; CN=castle.beginning-helidon.apress.com
   *  start date: Sep 17 19:39:40 2022 GMT
   *  expire date: Sep 16 19:39:40 2027 GMT
   *  issuer: C=CZ; ST=Prague; L=Prague; O=Apress; OU=BeginningHelidon; CN=beginning-helidon.apress.com
   ```