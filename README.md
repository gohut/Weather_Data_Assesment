# Weather_Data_Assesment
Processing a given CSV file, storing it in the DB

#Serivce-Name: Weather_app
This service will be Fetching the Data from the given CSV file, store it in the Weather_data_DB database.
Also enables printing the rows in the DB, sorting the DB based on the field name and type(ascending or decending)
#Controller Mappings 
For Uploading the CSV file: http://localhost:8089/weather-api/loadcsv
For viewing:  http://localhost:8089/weather-api/print
For Sorting: http://localhost:8089/weather-api/sort?field=<#FIELD_NAME>&type=<#ASC OR #DEC>
