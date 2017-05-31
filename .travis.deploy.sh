#!/bin/bash

mvn deploy --settings ./.travis.settings.xml -DrepositoryId=vaadin-snapshots -Dvaadin.spreadsheet.developer.license=$SPREADSHEET_LICENSE -Dvaadin.charts.developer.license=$CHARTS_LICENSE -Dvaadin.board.developer.license=$BOARD_LICENSE -DaltDeploymentRepository=vaadin-snapshots::default::https://oss.sonatype.org/content/repositories/vaadin-snapshots/ -DskipTests=true -DskipITs -B
