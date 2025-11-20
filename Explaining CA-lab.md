Here is an outline on what the fuck is going on with CA lab

## `/app`
More adequately: the fucking game, I believe.
### `AppBuilder.java`
- builds the UI (import from view)
	- create individual functions to add views
	- create individual functions to add use cases
	- create a `build` function that returns the JFrame (app)
- please use the `ViewManagerModel` and `ViewManager` thank you
	- it will be very useful if ever you want to switch views easily like structurally, our UI really looks like CA-Lab with the changing views and all that jazz
### `Main.java`
- build the app using the `AppBuilder`
- pack it and set visible

## `/data_access`

### `<puzzle>API.java`
> might be renamed but idk
- API access
- parse data from API
### `PlayerDataAccessObject.java`
> In this case it's named `FileGameDataAccessObject.java` but ehhhh
- reads local save data to obtain `Player` object
- saves current `Player` data to local
## `/entity`
- ...just, entities. idk do what you must.
- setters and getters usually.
- make sure that the entity does not contain complex-ish functions
  - e.g. movement, validation
## `/interface_adaptor`
### `ViewModel.java`
- Consists of `viewName` (name of the current view), a `PropertyChangeSupport` variable from `java.beans`, and an unknown `state`
	- the support is mainly to assist state changes
- setters, getters and `firePropertyChange`
	- `firePropertyChange` changes a specific "state", which for the most part is the view
### `ViewManagerModel.java`
- extends `ViewModel<String>` (`state = ""`)
- and has the view name "view manager" and an empty state
### `<item>Controller.java`
- contains an `execute` which executes the *use case interactor*
- may contain other functions that ==switches views==
	- *the view switching function lies in the **use case interactor** which runs the function in the **presenter***
- for puzzles, include other functions as needed, interacting with the *interactor* for the most part.
### `<item>Presenter.java`
- *implements **Output Boundary***
- prepares two views: on Success and on Fail
- updates states on success or fail
	- `firePropertyChange()` in the respective model if necessary
- switch views if necessary
	- change `state` at `viewManagerModel` to the target view name (taken from the model)
	- `firePropertyChange()` to switch the view
### `<item>State.java`
- contains `Player` variable and `locationMapping`... or something
- *the variable of this class will be updated on any success or failure... i think*
- used in `<item>Presenter` (typically to reset the state by declaring `new <item>State` or to update states)
- used in `<item>ViewModel` (the view model will contain this particular state)
### `<item>ViewModel.java`
- extends `ViewModel<State_Type>`
## `/use_case`
### `<item>InputBoundary.java`
> **Interface**
- contains the `execute` function
- implemented in the use case interactor
### `<item>InputData.java`
- only implemented if the use case takes input data from the user
	- and by that I mean puzzles and the navigation use case
### `<item>Interactor.java`
- *implements **Input Boundary***
- executes the "logic"
- can make use of the DAO (under Data Access Interface) and Presenter (Output Boundary)
	- May update any existing data
	- May lead to ==switching views== calling a function from the *presenter*
### `<item>OutputBoundary.java`
> **Interface**
- contains `prepareSuccessView` and `prepareFailView`
- and other relevant functions
### `<item>OutputData.java`
- only implemented if the use case produces an output
	- probably useful in puzzles imo
### `<item>DataAccessInterface.java`
> **Interface**
- whatever relevant function you need
- implemented by the DAO so base the functions on what you need in that DAO
## `/view`
- the UI
- can modify relevant states (i.e. `<item>state`)
- action listeners can execute the function in the controller (using data from state)
