# Applying genetic algorithm in university's scheduling
## Technology
- Java.
- GUI with Java Swing.
- MySQL(jdbc).
- MVC model.
## Algorithm design
### Problem
1. Input
- List of Classes, each Class includes:
    + A Teacher(contains a schedule where the Teacher cannot come to school).
    + Student Group(contains the number of Students).
    + Subject (this Subject requires Room type (with or without computers)).
    + Schedules where the Class should not take place.
- List of Rooms:
    + There are 2 types of Rooms: with computers and without.
    + Room capacity.
    + Schedules where the Room couldn't be used.
2. Ouput: schedule:
- All Classes are scheduled.
- Classes with many periods must take place continuously (for example: Math class has 3 periods so it must take place on 2-4, 6-8,...).
- Classes must end before the end of the day.
- One Room at one time can only hold one Class.
- A Group Student can only study in one Class at a time.
- A Teacher can only teach one Class at a time.
- Entities(Teacher, Class, Room) cannot be used during busy periods.
- The Class request type must be the same as the Room type.
- The number of Students in the Class must be less than or equal to the capacity of the Room.
- Minimize the number of days that Teachers come to school.
- Minimize Teacher free time during the day(between each Class).
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
