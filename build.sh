#!/bin/bash

rm -r cmp/
mkdir cmp
javac -d cmp --module-path javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml src/Game/*.java
