# Setup
1. Download the latest release from ![]()
2. Move the `.jar` file to your desired directory
3. `cd` your terminal into the directory containing your `.jar` file
4. Run the application by typing `java -jar filename.jar` into th teterminal

# Commands
For the command formats below, lower cases are the keywords and CAPS are the user inputs.

### `add`
Format: `add NAME AMOUNT`<br>
The add commands adds a transaction that NAME has made during the trip.<br>
TIP: If the input is wrong, you can either use the `delete` command or `add NAME -AMOUNT` to correct it. You can also check the list of transactions using the `list` command.

### `list`
Format: `list`<br>
Prints list of transactions. Each person's contribution will be aggregated.

### `delete`
Format: `delete NAME`<br>
Deletes entries in the transaction table with NAME.

### `calculate`
Format: `calcuate`<br>
Prints out the final list of (the minimum number of) transactions that are required to resolve the balancing of funds.
NOTE: If somebody did not contribute anything throughout the trip, do `add NAME 0` to include him/her into the calculation.

### `exit`
Format: `exit`<br>
Exits the program
