#!/bin/bash

mvn install -Dvaadin.spreadsheet.developer.license=$SPREADSHEET_LICENSE -Dvaadin.charts.developer.license=$CHARTS_LICENSE -Dvaadin.board.developer.license=$BOARD_LICENSE -Dvaadin.testbench.developer.license=$TESTBENCH_LICENSE -DskipTests=true -DskipITs
