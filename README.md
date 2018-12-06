# Damon
[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Download](https://api.bintray.com/packages/zyhang/maven/Damon/images/download.svg) ](https://bintray.com/zyhang/maven/Damon/_latestVersion)
[![API](https://img.shields.io/badge/API-15%2B-blue.svg?style=flat)](https://developer.android.com/about/versions/android-4.0.3)
[![Author](https://img.shields.io/badge/Author-zyhang-red.svg?style=flat)](https://www.zyhang.com/)

damon is an android [Model-View-Presenter](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) framework

## Features

- @RequiresPresenter注入多Presenter
- @BindPresenter赋值
- Presenter缓存以及状态恢复

## Usage

``` kotlin
@RequiresPresenter(value = [ExamplePresenter1::class, ExamplePresenter2::class, ExamplePresenter3::class])
class MultiPresenterActivity : MvpAppCompatActivity(), ExampleView1, ExampleView2, ExampleView3 {
    
    @BindPresenter
    private var presenter2: ExamplePresenter2? = null
    @BindPresenter
    private var presenter3: ExamplePresenter3? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_presenter)

        presenter?.log("presenter1 from getPresenter")
        presenter2?.log("presenter2 from @BindPresenter")
        presenter3?.log("presenter3 from @BindPresenter")
    }
}
```

## Installation

```groovy
implementation 'com.zyhang:damon:<latest-version>'
```

## Rx Kotlin Extension

- 支持自动dispose

```groovy
// rxjava support
implementation 'com.zyhang:damon-rxjava:<latest-version>'

// kotlin syntax
implementation 'com.zyhang:damon-rxjava-kotlin:<latest-version>'
```

## Thanks
[konmik/nucleus](https://github.com/konmik/nucleus/tree/master)
