# Dependency Injection with Dagger Hilt for Room Database

This is an implementation of the Dagger Hilt usage reference [here.](https://github.com/Sendiko/dagger-hilt-reference)

### How it used
---

I use the Dagger Hilt to provide Room Database, Dao, and Repository.
```kotlin
  @Module
  @Installin(SingletonComponent::class)
  object AppModule {
    @Provides
    @Singleton
    fun proviceAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "ticket_db")
            .build()
    }
    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): TicketDao {
        return appDatabase.dao()
    }
    @Provides
    @Singleton
    fun provideTaskRepository(ticketDao: TicketDao): TicketRepository{
        return TicketRepository(ticketDao)
    }
  }
```
Singleton is used to make sure that there are only 1 instance are created in on lifecycle. since i provide the database instace in the Hilt Module, i don't need to create an instance in the AppDatabase.kt, those there is only dao and database declaration.
```kotlin
  @Database(
    entities = [Ticket::class],
    version = 1,
    exportSchema = false
  )
  abstract class AppDatabase: RoomDatabase() {
    abstract fun dao(): TicketDao
  }
```

And for every depenency need, we can just add ```@Inject``` to the class's constructor.

```kotlin
  class TicketRepository @Inject constructor(private val ticketDao: TicketDao)
  ---
  class HomeScreenViewModel @Inject constructor(
    private val repository: TicketRepository
  ): ViewModel()
```

and with dagger hilt i can also just add ```@HiltViewModel``` to mark my viewmodel as hilt viewmodel for easier injection.
```kotlin
  @HiltViewModel  
  class HomeScreenViewModel @Inject constructor(
    private val repository: TicketRepository
  ): ViewModel()
  ---
  @Composable
  fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel()
  )
```

### The Injection Flow
---

So the flow of the dependency injection with dagger hilt that i understand is: 
```
AppModule define, and provide dependencies 
  -> classes that need the dependency can call @Inject to get the dependency(ies)
    -> and define @HiltViewModel to ViewModel to let easier instance creation
```
