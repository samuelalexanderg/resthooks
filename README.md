Byteflair Resthooks
===================

Status
------

|**Development Branch**|**Master Branch**|
|:--------------------:|:---------------:|
|[![Build Status](http://travis-ci.org/Byteflair/resthooks.svg?branch=dev)](http://travis-ci.org/Byteflair/resthooks)|[![Build Status](http://travis-ci.org/Byteflair/resthooks.svg?branch=master)](http://travis-ci.org/Byteflair/resthooks)|

Goals
-----
Byteflair Resthooks is a reference implementation of [Resthooks] (http://resthooks.org/)
with skinny payloads. This is the simplest implementation with the most applicability to most
use case scenarios. It is intended for the following purposes:
* Creating your own resthooks implementation without starting from scratch or even reusing
it as is
* Educate your self on resthooks or building APIs with Spring

Introduction
------------
The project is structured in three sub-projects:
* resthooks-core: Core classes of the resthooks implementation with an In Memoy data store.
* resthooks-mongodb: A sample mongo implementation for the resthooks data stores. It serves
as a good guide on how to use repository polimorfism to decide at runtime which implementation
to inject: in memory data store, mongo data store o maybe a JPA data store implementation.
* resthooks-supplier: Is a sample API supplier that supports resthooks. You can use it to
create custom subscriptions and trigger events to see how resthooks are invoked.

Running the Resthooks Supplier API
----------------------------------


