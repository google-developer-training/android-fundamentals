MinimalistContentProvider - Solution Code
=========================================

This sample presents a minimalist content provider implementation, just the
bare bones, to show how it's done.

Introduction
------------

One of the reaons content providers are difficult to understand is because
they require a lot of other parts to work in a useful way, such as a user
interface to display the data, adapters to drive the UI, a backend such as a
database, you get the idea.

This sample is not useful as an app. It's sole purpose is to show the
basic mechanics if implementing a content provider. As such, the data is
mocked, and the user interface is as simple as possible.

Pre-requisites
--------------

You need to know:
- It helps if you understand MVP architecture, which is all about
separating the data from the user interface, with a "presenter" that connects
the two; that is, shuffles the data from the repository to the user interface.

Getting Started
---------------

1. Download and run the code in Android Studio.

License
-------

Copyright 2016 Google, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
