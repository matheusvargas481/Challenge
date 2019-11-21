# Challenge Data Analysis

This application was developed a Data Analysis where there should be input files (follow the example in the folder Challenge/FileTestSystem) in the folder created by the application ~/data/in. 
After input the system generates a report with the following output in the path ~/data/out based on input files.
- 1 - Number of Customers
- 2 - Seller Quantity
- 3 - ID of the most expensive sale
- 4 - The worst seller name ever

This application keeps track of the ~/data/in path for any changes that occur, whether adding, modifying or deleting files.
It will update the report so that it always has updated output.

## Requirements

  - [Git](https://git-scm.com/)
  - [Gradle](https://gradle.org/)

## Installation

- Installing Git:
```
$ apt-get install git
```

- Clone the github project:
```
$ git clone https://github.com/matheusvargas481/Challenge.git
```

## Configuration

1. Access the folder ~/Challenge.

2. Run the script with the following command:
```
$ ./startDataAnalisys.sh {USING THE OPTIONS BELOW}
```
NOTE: The options must be written in lowercase.
- build: to generate the app build
- run: to run the application

## How to use the app

1. Insert your files in the format of the files found in the ~/Challenge/FileTestSystem. Folder within the folder created by the ~/data/in application
2. Track results in file created in ~/out folder

Ready, just follow the logs by the terminal!

To stop the application use the command in the Ctrl + C terminal.

## Author

**Matheus da S. Loreto de Vargas** -  [GitHub](https://github.com/matheusvargas481)