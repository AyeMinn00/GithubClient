# Github Client

## Development Environment

The app is written entirely in Kotlin and uses the Gradle build system.

Before Development, Please add Githu Token in ```local.properties```. <br>
For example, ```github.token="YOUR_TOKEN"``` <br>
According to Github api doc, using Token reduces rate limiting.

## Architecture
The architecture is built around
Android Architecture Components
and follows the recommendations laid out in the
[Guide to App Architecture](https://developer.android.com/jetpack/docs/guide). Logic is kept away
from Activities and Composable and moved to
[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)s.
Data is observed using
[Kotlin Flows](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow)

The **Repository** layer handles data operations.
Since the project is very small, **Domain** layer is omitted.

[Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/) are used to fetch data from server.

[Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) is used for infinite scroll.

[Coil](https://github.com/coil-kt/coil) is used for Image Caching and Loading, which uses modern libraries including Coroutines, OkHttp, Okio, and AndroidX Lifecycles.

[Navigation](https://developer.android.com/develop/ui/compose/navigation) is used to navigate between screens.

Dependency Injection is implemented with
[Hilt](https://developer.android.com/training/dependency-injection/hilt-android).

Asynchronous tasks are handled with
[coroutines](https://developer.android.com/kotlin/coroutines). Coroutines allow for simple
and safe management of one-shot operations as well as building and consuming streams of data using
[Kotlin Flows](https://developer.android.com/kotlin/flow).

Follows [Coroutines Best Practices in Android](https://developer.android.com/kotlin/coroutines/coroutines-best-practices).

[Detekt](https://detekt.dev/) is used as a  static code analyzer with a Formatting Plugin which wraps **Ktlint**.

[Twitter Compose Rules](https://twitter.github.io/compose-rules/) is used as a set of custom ktlint rules to ensure that the composables don't fall into common pitfalls.

## Testing
1. Unit tests use Junit4 and [MockK](https://mockk.io/)
2. [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) is used to test api services.
3. [Turbine](https://github.com/cashapp/turbine) is used for testing Coroutine Flow.
4. [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for injecting Repositorys.


## Possible Improvements
1. Adding more Testing for more Test Coverage
2. Support Adaptive Layout
3. Setting up CI CD
4. [Make app more accessible](https://developer.android.com/guide/topics/ui/accessibility/apps)
5. Offline support for better User Experience
6. Adding Firebase Crashlytics for Crash Report
7. Multi-languae and RTL Support
8. Add Baseline Profile which improves code execution speed by about 30% from the first launch by avoiding interpretation and just-in-time (JIT) compilation steps for included code paths.