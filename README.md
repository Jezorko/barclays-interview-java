# Vat rate printer

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Build Status](https://travis-ci.org/Jezorko/kofun.svg?branch=master)](https://travis-ci.org/Jezorko/barclays-interview-java)

## Overview

This application is my solution to the Barclay's Prague technical interview task.

### Task description

Implement an application in Java capable of printing out three EU countries with the lowest and three EU countries with the highest standard VAT rate as of today within the EU.

#### Input

http://jsonvat.com/

#### Suggestions

 * keep it clean and simple (yet with a reasonable design allowing future extendibility)
 * use any library of your choice
 * verify the program correctness
 * implement as a Maven or Gradle project
 * submit preferably as a GitHub repo link

## Compilation and execution

To compile the project, run:

```bash
mvn clean compile assembly:single
```

To run tests:

```bash
mvn clean test verify
```

To start compiled application:

```bash
java -jar 'target/barclays-interview-java-1.0-SNAPSHOT.jar'
```