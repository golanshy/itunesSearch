# itunesSearch

A demo app demonstrating usage of Dagger, Rx, MVP pattern & ViewModel.

The app downloads 100 records from the iTunes search API and allows the user to pull & refresh the content.
The results are rendered in a list in portrait mode and in a 2-coloumns grid in landscvape.

Each clicked item is shown in details and when the list resumes the colour changes to show the item was previously selected.

The user can also delete an item from the list and/or from the details page, this item will no loger show in the list, even when the list is refresshed.

By using ViewModel the functionality persists on configuration changes.
