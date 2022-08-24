# library_registry
mock backend for a library

## Brief
- build a console application that mocks a library system
- the user can see available books, read from a data file
- the user can loan a book, and see what books they have on loan
- an admin can run a report to show all currently loaned books, and the amount of times particular books have been loaned out

## Initial notes
- I want to use some design patterns I've been learning, and push my usage of the SOLID principles
- I plan on using the builder pattern to eliminate setters/getters, thus reducing coupling
- I plan on using dependency inversion where possible, again reducing coupling as much as possible
- I want to use interfaces for inheritance, to eliminate superclass fragility

![components](https://i.imgur.com/SxwhkJi.png)
