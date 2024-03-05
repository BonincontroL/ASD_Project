CC=gcc
MAX_HEIGHT?=30
run:
	mkdir -p bin
	$(CC) ./src/main.c ./src/skip_list.c  -o bin/main_ex2.out
	./bin/main_ex2.out ./src/dictionary.txt ./src/correctme.txt $(MAX_HEIGHT)

UnitTests:
	mkdir -p UnitTests
	$(CC) ./src/skip_list.c ./src/UnitTests.c  -o UnitTests/UnitTests.out
	./UnitTests/UnitTests.out
	
clear:
	rm -rf bin
	rm -rf UnitTests
	clear