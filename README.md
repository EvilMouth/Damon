# Damon
[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Download](https://api.bintray.com/packages/zyhang/maven/Damon/images/download.svg) ](https://bintray.com/zyhang/maven/Damon/_latestVersion)

damon is an android [Model-View-Presenter](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) framework

## Multi Presenter Version

- @RequiresPresenter支持多Presenter注入
- @BindPresenter替换getPresenter()

## Usage

``` kotlin
@RequiresPresenter(value = [ExamplePresenter1::class, ExamplePresenter2::class])
class MultiPresenterActivity : MvpAppCompatActivity(), ExampleView1, ExampleView2 {
    
    @BindPresenter
    private var presenter1: ExamplePresenter1? = null
    @BindPresenter
    private var presenter2: ExamplePresenter2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_presenter)

        presenter1?.log("presenter1 from @BindPresenter")
        presenter2?.log("presenter2 from @BindPresenter")
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
