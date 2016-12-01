ImplicitIntentsReceiver Sample App (Solution Code) 
============

The ImplicitIntentsReceiver app registers itself for implicit intents that come from 
browsable links (URLs) with the scheme:http and host:developer.android.com 
(see AndroidManifest.xml).

If it receives that intent, the app prints the incoming URI to a textview.
ImplicitIntentsReceiver is intended to be used in conjunction with the ImplicitIntents app, but
will receive a matching implicit intent from any app (for example, a link in an email.)


Pre-requisites
--------------

For this app you should be familiar with: 
* The ImplicitIntents app.
* Intent filters in the Android Manifest.
* Intents, intent data, and intent extras. 
* Starting activities with intents. 

Getting Started
---------------

1. Download and open this sample in Android Studio.

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
