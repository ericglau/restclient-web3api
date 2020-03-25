# MicroProfile application using Web3api for blockchain interaction

## Introduction

This project provides an example of how a cloud-native Java application can easily call a REST API through generated Java interfaces.  

The scenario demonstrates using [Amberdata's Web3api](https://docs.amberdata.io/reference#reference-getting-started) to interact with the Ethereum public blockchain to predict gas prices for transactions.

### Running the project

Run the project with the following command.  This runs the application in development mode allowing you to access it through the Open Liberty server, while any further changes that you make to the source code will be reflected automatically.

```
mvn liberty:dev
```

Open your browser at the following URL to invoke a servlet which accesses Web3api to return predicted gas prices for Ethereum.

http://localhost:9080/gas

### How it was built

- Project layout using [MicroProfile Starter](https://marketplace.visualstudio.com/items?itemName=MicroProfile-Community.mp-starter-vscode-ext)
- Java [interfaces](src/main/java/com/example/demo/api) and [models](src/main/java/com/example/demo/models) generated using this chain: [Web3api REST API for gas predictions](https://docs.amberdata.io/reference#get-gas-predictions) -> [OpenAPI document](https://app.swaggerhub.com/apis/ericglau/web3api-gas-predictions/1.0.0) -> [MicroProfile Rest Client Generator](https://marketplace.visualstudio.com/items?itemName=MicroProfile-Community.mp-rest-client-generator-vscode-ext)
- Simple [servlet](src/main/java/com/example/demo/GasServlet.java) making use of the generated REST client through [dependency injection](https://openliberty.io/guides/microprofile-rest-client.html), allowing you to simply call it as a Java interface and accessing the Java models directly.