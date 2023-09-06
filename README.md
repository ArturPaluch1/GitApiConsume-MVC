# GitApiConsume-MVC

MVC version of application, that consumes Github Api. The application was made with Java and SpringBoot. 
On main page user has a form to fullfil. User has radiobutton to select "accept" parameter - xml or json. Checking xml option will return 404 error with message: "XML is not acceptable". 
Json option, with correct username, that exists on GitHub platform application will show user a table contains all github repositories, which are not forks belongs to user with given username. 
For each repository also will show a list of its branches with names and the last commit sha.
When user pass username that does not exist in GitHub or will send empty value, api will return 406 error with message "User with this username do not exist on GitHub".
