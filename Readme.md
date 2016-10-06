# Enumadapter [![](https://jitpack.io/v/kikuchy/Enumadapter.svg)](https://jitpack.io/#kikuchy/Enumadapter)

The easiest way to make Enum's ListView or Spinner for Android.

![Enums to dropdown menu item from Resources](art/screenshot.png)


## Why use Enumadapter?

[ArrayAdapter](https://developer.android.com/reference/android/widget/ArrayAdapter.html) shows results of `Object.toString()`.
It's usefull to make list from `Enum.values()`, but we cannot shows localized Resource's string directly.

Enumadapter allows to you making Resource replesented Enum values list!!


## Usage

```java
// in your Activity
SpinnerAdapter adapter = new EnumArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Phones.values(), new ResourceStringify<Phones>(this) {
    @Override
    public int getStringResource(Phones target) {
        int res = 0;
        switch (target) {
            case IPHONE: res = R.string.iphone; break;
            case ANDROID:res = R.string.android; break;
            case WINDOWS_PHONE: res = R.string.windows_phone; break;
            case BLACKBERRY: res = R.string.blackberry; break;
            case FIREFOX_OS: res = R.string.firefox_os; break;
            case UBUNTU_PHONE: res = R.string.ubuntu_phone; break;
        }
        return res;
    }
});
```


## Install

Add it in your root build.gradle at the end of repositories:

```groovy
	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```

and add the dependency in your project local build.gradle

```groovy
	dependencies {
	        compile 'com.github.kikuchy:Enumadapter:0.0.1'
	}
```