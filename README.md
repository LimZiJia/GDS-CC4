# Transaction Balancer
If you have a group that takes turns to pay and split the bill later, this is the program for you! 
You can add a transactions to the program everytime someone pays and balancing the payments is as easy as typing `calculate`.
Also, don't worry about entering every transaction in one session because it is automatically saved for you!

## Features
* Add as many transactions as you want! They will be aggregated per person automatically.
*  Every `add` and `delete` automatically saves your transaction list.
*  Saved transaction list is loaded automatically on start up.
*  You can `list` all transactions to view the current transaction list.
*   delete all!` is useful to reset the program for next time.


# Setup
1. Make sure you have Java 11 or above installed in your computer
2. Download the latest ![release](https://github.com/LimZiJia/GDS-CC4/releases)
3. Move the `.jar` file to your desired directory
4. `cd` your terminal into the directory containing your `.jar` file
5. Run the application by typing `java -jar filename.jar` into th teterminal

# Commands
For the command formats below, lower cases are the keywords and CAPS are the user inputs.

### `add`
The add commands adds a transaction that NAME has made during the trip.<br>

Format: `add NAME AMOUNT`<br>

**TIP:** If the input is wrong, you can either use the `delete` command and `add NAME AMOUNT` again, or `add NAME -AMOUNT` to correct it. You can also check the list of transactions using the `list` command.

### `list`
Prints list of transactions. Each person's contribution will be aggregated.<br>

Format: `list`

### `delete`
Deletes entries in the transaction table with NAME.<br>
Deletes all enties if argument is `all!`<br>

Format: `delete NAME` or `delete all!`<br>

### `calculate`
Prints out the final list of (the minimum number of) transactions that are required to resolve the balancing of funds.<br>

Format: `calcuate`<br>

NOTE: If somebody did not contribute anything throughout the trip, do `add NAME 0` to include him/her into the calculation.
Similarly, if somebody should not be included in the final transaction, they should not have an entry in the transactions list. Do `delete NAME` to remove them.

### `exit`
Exits the program<br>

Format: `exit`<br>
