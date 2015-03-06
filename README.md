# Couchbase Migration Tooling

This project aims to provide tools which help you to migrate from relational database systems to Couchbase.

## Core Requirements

* It should be possible to extract the schema  from the relational source DBS
  * Database / Schema (Some RDBMS dont seperate data into databases but schemas.)
  * Tables
  * Column definitions
  * Primary Keys
  * Relationships (based on Foreign Key definitions)
* We want to select only a subset of tables those should be migrated
* It should be possible to map the previously extracted (and selected) schema (parts) to a JSON structure
  * Define and customize the key pattern (E.G. table name + '::' + primary key value)
  * Decide per relationship how it will be reflected in JSON. Options are a 'embedded JSON' or 'referenced documents'
* We want to transfer the data over from the source database to the target bucket based on the previously defined mapping
  * It should be possible for the user to select the target bucket

## Implementation Idea

The idea is to implement a core library in the first step. The core library will use JDBC in order to extract the schema meta data and data. It will create an internal unified schema model of the the source database. In the next step we can transform / map this model to another one which is reflecting our JSON structure. 

In the next step we will provide a simple Command Line Interface in order to use the core library.

In the final step this Core Library is used by a Graphical Tool. The tool will be realized as a Eclipse-Plug-in. We can imagine features like 'Output the source model graphically' and then set some parameters on the several compoents (E.G. relationship, Key) how to map them to a JSON structure. A preview will be available which already shows a JSON example. So our Plug-in comes with multiple 'Views'.

## Current State

The project is in a conceptional state. We will start to implement the core library soon.
