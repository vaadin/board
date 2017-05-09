#!/bin/bash

mvn install -Dvaadin.board.developer.license=$BOARD_LICENSE -Dvaadin.testbench.developer.license=$TESTBENCH_LICENSE -DskipTests=true -DskipITs
