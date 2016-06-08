# Espresso Descendant Actions

Adding support for accessing descendant views. Useful for accessing descendant views of the root view at a particular position in a RecyclerView.

[ ![Download](https://api.bintray.com/packages/joerogers/maven/espresso-descendant-actions/images/download.svg) ](https://bintray.com/joerogers/maven/espresso-descendant-actions/_latestVersion)

Basic Usage
-----------

Checking state of a descendant view:

```
onView(withId(R.id.recyclerView))
     .perform(
          actionOnItemAtPosition(5, DescendantViewActions.checkViewAction(
               selectedDescendantsMatch(withId(R.id.favoriteButton),
                                withContentDescription(R.string.favorite))))
     );
```


Performing an action on a descendant view:

```
onView(withId(R.id.recyclerView))
     .perform(
          actionOnItemAtPosition(5,
               DescendantViewActions.performDescendantAction(withId(R.id.favoriteButton), click()))
     );
```

Gradle
------

```
dependencies {
    compile 'com.forkingcode.espresso.contrib:espresso-descendant-actions:{version listed above}'
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
    
