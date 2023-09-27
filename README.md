# Applying genetic algorithm in university's scheduling
## Technology
- Java.
- GUI with Swing library.
- MySQL(jdbc).
- MVC model.
## Algorithm design
### Problem
1. Input
- A list of classes, each class contains:
    + A teacher(with busy time that the teacher can't go to school).
    + Student groups(with number of students).
    + A subject(this subject requires the type of the room(has computer or not)).
    + Busy time of the class.
- A list of rooms:
    + 2 types of room: has computer and not.
    + capacity of the room.
    + busy time of the room that a lecture can't happen.
2. Ouput: schedules that:
- All the classes is scheduled.
- Multi-period class must happen continuously(ex: Math class has 3 period so it must happen on 2-4, of 8-10,...).
- A schedule of a class must end before the end of the day.
- One room at one time can only hold one class.
- A student group at one time can only study in one class.
- A teacher at one time can only teach one class.
- The entity(teacher, class, room) can't be used in the busy time.
- A class's requiring type should be the same as the room's type.
- A class's student count should smaller or equal to the room's capacity.
- Minium the days count that a teacher go to school.
- Minium free time of a teacher in a day.
### Main algorithm
1. Create initial population(schedules).
2. Fitness function calculation.
3. Select parents.
4. Crossover.
5. Mutation.
6. Survivor selection.
7. Loop from step 2 until termination criteria reached.
### Chromosome design
#### Main idea
![Chromosome design implement](https://i.imgur.com/RDVIej0.png)
#### The way to implement
![Chromosome design implement](https://i.imgur.com/E75eA2b.png)
#### Crossover
![Crossover](https://i.imgur.com/7TIARml.png)
#### Mutation
![Mutation](https://i.imgur.com/D5tSW9W.png)
#### Database design(Sql diagram)
![Sql diagram](https://i.imgur.com/ElrEL51.png)
#### GUI and result
- Preparing
![result](https://i.imgur.com/hP6jXPr.png)
- Scheduling
![result](https://i.imgur.com/LcUJajA.png)
- Re-check schedule
![result](https://i.imgur.com/Pzz2aGE.png)
- Applying the schedule
![result](https://i.imgur.com/UXlfD4S.png)
- Query schedule
![result](https://i.imgur.com/S38Jf8C.png)
- Account's info
![result](https://i.imgur.com/Vk65eHv.png)
- And other features...
