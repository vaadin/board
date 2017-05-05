#!/bin/bash

mvn deploy --settings ./.travis.settings.xml -DrepositoryId=vaadin-snapshots -Dvaadin.board.developer.license=$LICENSE -DaltDeploymentRepository=vaadin-snapshots::default::https://oss.sonatype.org/content/repositories/vaadin-snapshots/ -DskipTests=true -DskipITs -B
