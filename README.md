# Damon
[![Apache 2.0 License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0.html)
[![Download](https://api.bintray.com/packages/zyhang/maven/Damon/images/download.svg) ](https://bintray.com/zyhang/maven/Damon/_latestVersion)

damon is an android [Model-View-Presenter](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93presenter) framework

## Multi Presenter Version

- @RequiresPresenter支持多Presenter注入
- 增加@BindPresenter替换getPresenter()

## Usage

``` kotlin
@RequiresPresenter(value = [ExamplePresenter1::class, ExamplePresenter2::class])
class MultiPresenterActivity : MvpAppCompatActivityRx(), ExampleView1, ExampleView2 {
    
    @BindPresenter
    private var presenter1: ExamplePresenter1? = null
    @BindPresenter
    private var presenter2: ExamplePresenter2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multi_presenter)

        presenter1?.logByView("presenter1 from @BindPresenter")
        presenter2?.logByView("presenter2 from @BindPresenter")
    }
}
```

## Installation
```
implementation 'com.zyhang:damon:2.0.0-alpha'

// rxjava support
implementation 'com.zyhang:damon-rxjava:2.0.0-alpha'

// kotlin syntax
implementation 'com.zyhang:damon-rxjava-kotlin:2.0.0-alpha'
```

## Thanks
[konmik/nucleus](https://github.com/konmik/nucleus/tree/rx2)
