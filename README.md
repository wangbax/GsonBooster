# GsonBooster

GsonBooster是一个注解处理器，能够在编译期自动生成**兼容Kotlin特性的**、**高性能TypeAdapter**，以提升Gson的序列化与反序列化时间性能。

> 本仓库 fork 自 [spirytusz/GsonBooster](https://github.com/spirytusz/GsonBooster)，升级支持 **Kotlin 2.0** + **KSP 2.x**。

## Requirements

| 依赖 | 版本 |
|------|------|
| Kotlin | 2.0.21+ |
| Gradle | 8.9+ |
| AGP | 8.5.0+ |
| KSP | 2.0.21-1.0.28+ |
| compileSdk | 34+ |

## Download

### KAPT

```kotlin
plugins {
    kotlin("kapt")
}

dependencies {
    implementation("io.github.wangbax:booster-annotation:2.0.1")
    kapt("io.github.wangbax:booster-processor:2.0.1")
}

kapt {
    arguments {
        // 指定生成TypeAdapterFactory的全限定名，不指定则不生成
        arg("factory", "com.spirytusz.booster.BoosterTypeAdapterFactory")
    }
}
```

### KSP (推荐)

根目录 build.gradle.kts：

```kotlin
plugins {
    id("com.google.devtools.ksp") version "2.0.21-1.0.28" apply false
}
```

app 模块 build.gradle.kts：

```kotlin
plugins {
    id("com.google.devtools.ksp")
}

dependencies {
    implementation("io.github.wangbax:booster-annotation:2.0.1")
    ksp("io.github.wangbax:booster-processor-ksp:2.0.1")
}

ksp {
    // 指定生成TypeAdapterFactory的全限定名，不指定则不生成
    arg("factory", "com.spirytusz.booster.ksp.BoosterTypeAdapterFactory")
}
```

> KSP 2.x 不再需要手动添加 `sourceSets`，生成的代码会被自动识别。

## Usage

```kotlin
// 增加注解
@Boost
data class Foo(
    val string: String = ""
)

// 生成的TypeAdapterFactory注册到gson实例中
val gson = GsonBuilder()
        .registerTypeAdapterFactory(BoosterTypeAdapterFactory())
        .create()
```

## Why

### Benchmark

* OS: Android 10
* CPU: Snapdragon 855
* Core: 4

经过预热的场景下，可以看到耗时从毫秒级降低到了微秒级(横坐标为实验次数，纵坐标为耗时，单位微秒(us)):

![](img/time_cost_compare.svg)

可以从火焰图中看到反射部分（ReflectiveTypeAdapterFactory.create）被优化掉，耗时大幅缩短。

![](img/compare.png)

## Changelog

### 2.0.1
- 升级 Kotlin 2.0.21、Gradle 8.9、AGP 8.5.0、KSP 2.0.21-1.0.28
- `kotlinx-metadata-jvm` 迁移至 `kotlin-metadata-jvm` 2.0 API
- 修复 kapt 处理器遇到 Java 超类（无 `@Metadata`）崩溃的问题
- 修复 KSP 扫描器未过滤 `kotlin.Any` 超类型的问题
- 修复星号投影（`List<*>`）导致的 NPE
- group 变更为 `io.github.wangbax`

## License
```
MIT License

Copyright (c) 2021 ZSpirytus

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
```
