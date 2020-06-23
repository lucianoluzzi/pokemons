# Pokémons application

## The application

I would like to say this is a state of the art application, but when it comes to programming much of it is personal style.
With that out of the way, I tried to make the application as fresh as possible in terms of tools and patterns. 
More about that below.

## The architecture

My architecture pattern of choice for the application was the MVVP (as in the guidelines) with Clean Architecture.
I used the UseCase concept to prevent having ViewModels that do more than asking for data and delivering UI relevant entities, [avoiding God ViewModel](https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576).

I choose to expose the data to the screens with specific sealed classes that represents the [state of the screen](https://medium.com/better-programming/how-to-use-kotlin-sealed-classes-for-state-management-c1cfb81abc6a) - *Success* or *Error*.

For our sole feature, the classes are separated into ui, data or domain packages.
This contributes to separation of concerns and is a step in the right direction to make it scalable and future proof.

## Extras

### Modularization
Since the conception of the project I had planned to make it modular, not only because it’s a hot topic now in the android community, but because it overall contributes to the separation of concerns, makes build time smaller, makes harder for different teams to step into each others toes… It’s again a decision thinking how this could scale up better and cleaner.

![Image for the app's module structure](https://github.com/lucianoluzzi/pokemons/blob/master/modules.jpeg)

### Unit and UI testing
This is a no brainer, every application should be test covered.
The less you test today the more you will suffer in the future, either because you will need to cover this ever growing codebase or because features will stop working once you refactor them.

### Accessibility
Making apps accessible is important to promote inclusion, and with the increase of digital channels into our day-to-day activity, we can expect a growing user base of visually impaired people.
If that’s not incentive enough, [all digital apps are expected to be accessible by June 2021](https://ec.europa.eu/digital-single-market/en/news/digital-inclusion-and-web-accessibility-brochure).
I made sure the layout is designed in a way which the content will be read in the correct order.
Also provided specific content description for texts and images instead of using generic description.

### Dark mode
Even though I’m not providing specific drawables for Dark mode, the app is usable with it and it will follow the system Light/Dark theme.
Did so by extendind MaterialTheme.DayNight and providing specific night color for texts. 


## Obstacles I found and were it could improve
### ApolloAndroid and Gradle KotlinScript 
When I started the project, along with the modularization, I planned on using kotlin script on gradle files, with the intent of having the dependencies on a kotlin file, and having the benefit of auto-complete and navigate to definition.

Turns out apollo-android is not exactly a plug-and-play library, and making it work with this kind of setup was a challenge.
To make things better, I only discovered this after creating the whole modules structure with kotlin for the gradle files, you can check this initial attempt here: https://github.com/lucianoluzzi/fantastic_cockfight (maybe it was for the best, not sure about this project naming...)

### GraphQL
It’s great to be able to change what you receive from the backend on the fly, but when it comes to reusing the auto-generated entity objects all the way from the repository to the UI, I’m not sure.

It involves doing a lot of null checks, and having to declare extension functions for additional behaviour you want for that entity.
For example, I wanted to have different attributes for the visual description of the pokemon number and it’s accessibility description.
Because of this, I created my own ui models as a mirror of graphQL entities and provided it to the screen, feels like a little bit of boilerplate, so maybe this could be rethinked.

Also, ideally each feature should hold its own query, but since the queries have to be in the same folder as the schema, I had to keep them in the network module. Not the best.
In a REST application I would keep the service into the feature and create the query with a “NetworkProvider” provided by the network module.

These two problems are the most important, since it would impact how the application would grow, but I would also point the need to polish the layout a little bit more and add pagination.

## Teck-stack
Besides the standard jetpack libraries that every app uses, I also used the navigation component, apollo-android, databinding, espresso, junit5, koin, mockito and coil.

