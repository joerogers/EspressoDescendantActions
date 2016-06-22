# Espresso Descendant Actions

Adding support for accessing descendant views. Useful for accessing descendant views of the root view at a particular position in a RecyclerView.

[ ![Download](https://api.bintray.com/packages/joerogers/maven/espresso-descendant-actions/images/download.svg) ](https://bintray.com/joerogers/maven/espresso-descendant-actions/_latestVersion)

For history on this project see my [blog post](http://blog.forkingcode.com/2016/06/espresso-and-recyclerview.html)

Basic Usage
-----------

Checking state of the specific adapter view at position 5:

```
onView(withId(R.id.recyclerView)).perform(
    actionOnItemAtPosition(5,
        DescendantViewActions.checkViewAction(matches(isCompletelyDisplayed())))
);
```

Checking state of a descendant view:

```
onView(withId(R.id.recyclerView)).perform(
    actionOnItemAtPosition(5,
        DescendantViewActions.checkDescendantViewAction(
            withId(R.id.favoriteButton), matches(withContentDescription(R.string.favorite))))
);
```


Performing an action on a descendant view:

```
onView(withId(R.id.recyclerView)).perform(
    actionOnItemAtPosition(5,
        DescendantViewActions.performDescendantAction(withId(R.id.favoriteButton), click()))
);
```

See the sample project for a functional example.


Gradle
------

```
dependencies {
    androidTestCompile 'com.forkingcode.espresso.contrib:espresso-descendant-actions:{version}'
}
```

If you need to exclude support-annotations use this form:

```
dependencies {
    androidTestCompile('com.forkingcode.espresso.contrib:espresso-descendant-actions:{version}') {
        exclude module: 'support-annotations'
    }
}
```


License
-------

    Copyright (C) 2016 Joe Rogers

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
