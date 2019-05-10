# objectia-java 
[![Build Status](https://travis-ci.org/objectia/objectia-java.svg?branch=master)](https://travis-ci.org/objectia/objectia-java)
<!--[![codecov](https://codecov.io/gh/objectia/objectia-java/branch/master/graph/badge.svg)](https://codecov.io/gh/objectia/objectia-java)-->

Java client for [Objectia API](https://objectia.com)&reg;

## Documentation

See the [Java API docs](https://docs.objectia.com/guide/java.html).


## Requirements

Java 1.7 or later.


## Installation

### Maven

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.objectia</groupId>
  <artifactId>objectia-java</artifactId>
  <version>0.9.1</version>
</dependency>
```

### Gradle

Add this dependency to your project's build file:

```groovy
compile "com.objectia:objectia-java:0.9.1"
```

### Others

You'll need to manually install the following JARs:

* The objectia JAR from https://github.com/objectia/objectia-java/releases/latest
* [Google Gson](https://github.com/google/gson) from <https://repo1.maven.org/maven2/com/google/code/gson/gson/2.8.2/gson-2.8.2.jar>.

### [ProGuard](http://proguard.sourceforge.net/)

If you're planning on using ProGuard, make sure that you exclude the objectia bindings. You can do this by adding the following to your `proguard.cfg` file:

```
-keep class com.objectia.** { *; }
```

## Usage

Example.java

```java
package examples;

import com.objectia.ObjectiaClient;
import com.objectia.api.GeoLocation;
import com.objectia.exceptions.APIException;
import com.objectia.exceptions.ResponseException;

public class Example {
    public static void main(String[] args) {
        String apiKey = System.getenv("OBJECTIA_APIKEY");

        try {
            ObjectiaClient.init(apiKey);
            GeoLocation location = GeoLocation.get("8.8.8.8");
            System.err.println("Country code: " + location.getCountryCode());
        } catch (ResponseException ex) {
            System.err.println("Response error: " + ex.getMessage());
        } catch (APIException ex) {
            System.err.println("API error: " + ex.getMessage());
        }
    }
}
```


## License and Trademarks

Copyright (c) 2018-19 UAB Salesfly.

Licensed under the [MIT license](https://en.wikipedia.org/wiki/MIT_License). 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

Objectia is a registered trademark of [UAB Salesfly](https://www.salesfly.com). 