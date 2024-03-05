CC=gcc -Ofast
K?=25
FIELD?=2

run:
	mkdir -p bin
	$(CC) ./src/main.c ./src/mbi.c -o bin/main_ex1.out
	./bin/main_ex1.out ./src/records.csv src/results.csv $(K) $(FIELD)
	
clear:
	rm -rf bin
	rm -rf UnitTests
	clear

UnitTests: 
	mkdir -p UnitTests
	$(CC) ./src/Unit_Test.c ./src/mbi.c  -o UnitTests/UnitTests.out
	./UnitTests/UnitTests.out


