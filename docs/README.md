# NUSYapBot User Guide
Do you always have a lot to say? Let me guess, you like to: 

1. _speak_ your mind
2. let others people to _know_ your opinion
3. _share_ what you have with other people! 
but most of the times, few people listen 😞

## Meet NUSYapBot!
More than just a task manager, NUSYapBot is just like a helpful friend who is willing to lend you an ear, and help you with many other things in your daily life! Features of NUSYapBot include (but not limited to):

- Record your to-do list
- Specify deadline, or timing for important tasks
- Mark task as done

all of this are possible, when you run the application file with:
`java -jar NUSYapBot.jar`


Perfect 👍 Here’s the user guide rewritten in **GitHub Flavored Markdown (GFMD)**, with the date format specification included.

---

# User Guide

## Exiting the program : `bye`

Ends the chatbot session and closes the application.

**Format:**

```
bye  
```

---

## Viewing the task list : `list`

Displays all the tasks currently stored in the chatbot, including their type, status, and details.

**Format:**

```
list  
```

---

## Adding a todo : `todo <name>`

Adds a simple to-do task without any date or time.

**Format:**

```
todo <name>  
```

**Example:**

```
todo buy groceries  
```

---

## Adding a deadline : `deadline <name> /by <date>`

Adds a task with a specific deadline.

**Format:**

```
deadline <name> /by <date>  
```

**Example:**

```
deadline submit report /by 30-09-2025 2359  
```

**Date format must be one of the following:**

1. `DD-MM-YYYY TTTT`
2. `DD-MM-YYYY`
3. `DD-MM-YY TTTT`
4. `DD-MM-YY`

---

## Adding an event : `event <name> /from <date> /to <date>`

Adds an event task with a start and end time.

**Format:**

```
event <name> /from <date> /to <date>  
```

**Example:**

```
event project meeting /from 05-10-2025 1000 /to 05-10-2025 1200  
```

**Date format must be one of the following:**

1. `DD-MM-YYYY TTTT`
2. `DD-MM-YYYY`
3. `DD-MM-YY TTTT`
4. `DD-MM-YY`

---

## Deleting a task : `delete <tasknumber>`

Deletes the specified task from the list.

**Format:**

```
delete <tasknumber>  
```

**Example:**

```
delete 3  
```

---

## Finding tasks : `find <keyword>`

Searches for tasks containing the specified keyword in their description.

**Format:**

```
find <keyword>  
```

**Example:**

```
find report  
```

---

## Marking a task as done : `mark <tasknumber>`

Marks the specified task as completed.

**Format:**

```
mark <tasknumber>  
```

**Example:**

```
mark 2  
```

---

## Sorting tasks : `sort <field> <asc/desc>`

Sorts tasks based on the given field, either in ascending or descending order.

* Supported fields: `name`, `date`, `type`
* Order: `asc` (ascending), `desc` (descending)

**Format:**

```
sort <field> <asc/desc>  
```

**Example:**

```
sort date asc  
```

---

That's all for now, thanks for reading!
I would appreciate if you let me know any bug that you have encountered when using the app.

Before you leave, I would like to end with my favorite quote from Bruce Lee,
> “If you spend too much time thinking about a thing, you’ll never get it done.” — Bruce Lee

So... Jot them down and get started now!