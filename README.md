# Sushi Belt Game 

This code contains a complete, functional sushi restaurant game. In the game, there are 20 positions on the sushi belt, 5 customers sitting evenly spread out at different positions, and 4 opponent sushi chefs. Each chef is allowed to make and place one plate of sushi per rotation. Each chef starts with $100 in their account, placing a plate on the belt subtracts from there account. If a costumer both takes the plate and consumes the plate, the chef who made the plate is credited with the price of the plate.

This game includes an interface for creating a scoreboard, a visualization of a sushi belt, an interface for creating a few specific types of sushi on specific plate colors placed nearest to a specific position, a rotate button, and a message area where error messages are displayed. 

Each sushi plate object is associated with and can report the chef that made it. A plate of sushi will spoil after it has remained on the belt for a particular number of rotations depending on its ingredients, at which time it is removed from the belt automatically.

The Belt object is an observable with respect to belt events that represent rotation, a chef placing a plate on the belt, a customer buying a plate off the belt, or a plate expiring due to spoilage.

Below is a screenshot of my UI upon immediately running the Java application, as well as a screenshot after a few rotations. 

![Capture](https://user-images.githubusercontent.com/46548925/81461508-579a7780-917a-11ea-89b8-4ae3060b3f9d.JPG)
![Capture2](https://user-images.githubusercontent.com/46548925/81461511-59643b00-917a-11ea-87ca-2658a8f3a312.JPG)
