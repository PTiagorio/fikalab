//0x4d55C4056b1C6A153Ac6f5C4781170bd7e341C77

*PRE-REQUESITES*
solc --> npm install -g solc


creating maven project and adding web3j dependencies: (change web3j version accordingly)
	https://kauri.io/article/b9eb647c47a546bc95693acc0be72546/connecting-to-an-ethereum-client-with-java-eclipse-and-web3j

web3j docs for compiling and wrapper generators:
	https://web3j.readthedocs.io/en/latest/smart_contracts.html

generating java contract command:
web3j solidity generate -a=Contract_sol_test.abi -b=Contract_sol_test.bin -o ~/Desktop/fika/smartContract -p=SmartContract


//RUN raspEstatico APP#################

to update the jar file run: ./createJar.sh

java -cp web3jApp-1.0-SNAPSHOT-jar-with-dependencies.jar eth.Main



