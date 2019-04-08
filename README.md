# itunesSearch

A demo app demonstrating usage of Dagger, Rx, MVP pattern & ViewModel.

The app downloads 100 record from iTunes search API and allows the user to pull & refresh the data.
The results are shown in a list in portrait mode and in q 2-coloumnw grid in landscvape.
Each clicked item is shown in details and when the list resumes the colour changes to show the item was previously selected.

The user can also delete an item from the list and from the details page, this item will no loger show in the list, even when the list is refresshed.

By using ViewModel the functionality persists even on configuration change.
