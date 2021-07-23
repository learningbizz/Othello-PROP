LIBS = lib/junit.jar:lib/hamcrest.jar:lib/json.jar
MODS = javafx.controls,javafx.fxml
DRIVER ?= User
DRIVERDW = $(shell echo $(DRIVER) | sed -e "s/\b\(.\)/\l\1/g")

default: compile-all
.SILENT: default

othello: build
	java -cp $(LIBS):bin --module-path lib/$(HOSTOS) --add-modules $(MODS) cmd.othello
.SILENT: othello

driver: build
	java -cp $(LIBS):bin cmd.driver.$(DRIVERDW)
.SILENT: driver

test-ranking: build
	java -cp $(LIBS):bin org.junit.runner.JUnitCore test.unitary.RankingJUnit
.SILENT: test-ranking
.PHONY: test-ranking

test-entry: build
	java -cp $(LIBS):bin org.junit.runner.JUnitCore test.unitary.EntryJUnit
.SILENT: test-entry
.PHONY: test-entry

run-othello: compile-othello
	clear
	java -jar dist/$(HOSTOS)Othello/$(HOSTOS)Othello.jar
.SILENT: run-othello

run-driver: compile-driver
	clear
	java -jar dist/$(DRIVER)Driver/$(DRIVER)Driver.jar
.SILENT: run-driver

run-test-ranking: compile-test-ranking
	clear
	java -jar dist/RankingJUnit/RankingJUnit.jar
.SILENT: run-test-ranking

run-test-entry: compile-test-entry
	clear
	java -jar dist/EntryJUnit/EntryJUnit.jar
.SILENT: run-test-entry

doc:
	doxygen Doxyfile
.SILENT: doc
.PHONY: doc

clean:
	rm -rf bin dist
	cp ./res/defaults/defaultGames.json ./res/databases/games.json
	cp ./res/defaults/defaultConfigurations.json ./res/databases/configurations.json
	cp ./res/defaults/defaultPlayers.json ./res/databases/players.json
	cp ./res/defaults/defaultRankings.json ./res/databases/rankings.json
.SILENT: clean

compile-othello: build
	jar cvmf src/cmd/othello.mf $(HOSTOS)Othello.jar -C bin .
	mkdir -p dist dist/$(HOSTOS)Othello dist/$(HOSTOS)Othello/lib dist/$(HOSTOS)Othello/res 
	cp -R lib/*.jar dist/$(HOSTOS)Othello/lib
	cp -R lib/$(HOSTOS)/* dist/$(HOSTOS)Othello/lib
	cp -R res/databases dist/$(HOSTOS)Othello/res
	mv $(HOSTOS)Othello.jar dist/$(HOSTOS)Othello
.SILENT: compile-othello

compile-driver: build
	jar cvmf src/cmd/driver/$(DRIVERDW).mf $(DRIVER)Driver.jar -C bin .
	mkdir -p dist dist/$(DRIVER)Driver dist/$(DRIVER)Driver/lib dist/$(DRIVER)Driver/res 
	cp -R lib/*.jar dist/$(DRIVER)Driver/lib
	cp -R res/fixtures dist/$(DRIVER)Driver/res
	mv $(DRIVER)Driver.jar dist/$(DRIVER)Driver
.SILENT: compile-driver

compile-test-ranking: build
	jar cvmf src/cmd/unitary/ranking.mf RankingJUnit.jar -C bin .
	mkdir -p dist dist/RankingJUnit dist/RankingJUnit/lib
	cp -R lib/*.jar dist/RankingJUnit/lib
	mv RankingJUnit.jar dist/RankingJUnit
.SILENT: compile-test-ranking
.PHONY: compile-test-ranking

compile-test-entry: build
	jar cvmf src/cmd/unitary/entry.mf EntryJUnit.jar -C bin .
	mkdir -p dist dist/EntryJUnit dist/EntryJUnit/lib
	cp -R lib/*.jar dist/EntryJUnit/lib
	mv EntryJUnit.jar dist/EntryJUnit
.SILENT: compile-test-entry
.PHONY: compile-test-entry

build:
	javac -cp $(LIBS) --module-path lib/$(HOSTOS) --add-modules $(MODS) -d bin src/cmd/*.java \
							 src/cmd/driver/*.java \
							 src/cmd/unitary/*.java \
							 src/domain/*.java \
							 src/repository/*.java \
							 src/test/driver/*.java \
							 src/test/unitary/*.java \
							 src/util/*.java \
							 src/view/*.java
	mkdir -p bin/res
	cp -R src/view/template bin/view
	cp -R res/assets bin/res
.SILENT: build

compile-all:
	make compile-othello HOSTOS=Windows
	make compile-othello HOSTOS=MacOS
	make compile-othello HOSTOS=Linux
	make compile-driver DRIVER=Pair
	make compile-driver DRIVER=Bot
	make compile-driver DRIVER=User
	make compile-driver DRIVER=Configuration
	make compile-driver DRIVER=Game
	make compile-driver DRIVER=Board
	make compile-driver DRIVER=EasyDifficulty
	make compile-driver DRIVER=MediumDifficulty
	make compile-driver DRIVER=HardDifficulty
	make compile-test-ranking
	make compile-test-entry
.SILENT: compile-all

help:
	echo "Detected OS: $(HOSTOS)"
	echo "List of tasks:"

	echo ""

	echo "- (default):\t\tbuild Othello applications, drivers and JUnit JARs to dist/ folder"

	echo ""

	echo "- othello:\t\texecute the Othello application"
	echo "- driver:\t\texecute the driver specified by DRIVER=<Driver>"
		echo "\t\t\t\t- make driver DRIVER=Pair"
		echo "\t\t\t\t- make driver DRIVER=Bot"
		echo "\t\t\t\t- make driver DRIVER=User"
		echo "\t\t\t\t- make driver DRIVER=Configuration"
		echo "\t\t\t\t- make driver DRIVER=Game"
		echo "\t\t\t\t- make driver DRIVER=Board"
		echo "\t\t\t\t- make driver DRIVER=EasyDifficulty"
		echo "\t\t\t\t- make driver DRIVER=MediumDifficulty"
		echo "\t\t\t\t- make driver DRIVER=HardDifficulty"

	echo ""

	echo "- test-ranking:\t\texecute JUnit ranking tests"
	echo "- test-entry:\t\texecute JUnit entry tests"

	echo ""

	echo "- run-othello:\tbuild and execute the Othello application JAR"
	echo "- run-driver:\t\tbuild and execute the driver specified by DRIVER=<Driver> JAR"
	echo "- run-test-ranking:\tbuild and execute the JUnit ranking tests application JAR"
	echo "- run-test-entry:\tbuild and execute the JUnit entry tests application JAR"

	echo ""

	echo "- compile-othello:\tbuild the Othello application JAR to dist/ folder"
	echo "- compile-driver:\tbuild the driver specified by DRIVER=<Driver> JAR to dist/ folder"
		echo "\t\t\t\t- make compile-driver DRIVER=Pair"
		echo "\t\t\t\t- make compile-driver DRIVER=Bot"
		echo "\t\t\t\t- make compile-driver DRIVER=User"
		echo "\t\t\t\t- make compile-driver DRIVER=Configuration"
		echo "\t\t\t\t- make compile-driver DRIVER=Game"
		echo "\t\t\t\t- make compile-driver DRIVER=Board"
		echo "\t\t\t\t- make compile-driver DRIVER=EasyDifficulty"
		echo "\t\t\t\t- make compile-driver DRIVER=MediumDifficulty"
		echo "\t\t\t\t- make compile-driver DRIVER=HardDifficulty"
	echo "- compile-test-ranking:\tbuild the JUnit ranking tests application JAR to dist/ folder"
	echo "- compile-test-entry:\tbuild the JUnit entry tests application JAR to dist/ folder"

	echo ""

	echo "- doc:\t\t\tbuild doxygen documentation"
	echo "- clean:\t\tclean binaries and databases"
.SILENT: help

ifeq ($(OS),Windows_NT)
HOSTOS := Windows
else
UNAME_S := $(shell uname -s)
	ifeq ($(UNAME_S),Darwin)
HOSTOS := MacOS
	else
HOSTOS := Linux
	endif
endif
