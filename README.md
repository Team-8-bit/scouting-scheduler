# scouting-scheduler
Writes event-specific scouting information to a google sheet for later access

---

# Running
You will need to generate these credentials and place them in the resources directory
- [Google service account](https://cloud.google.com/iam/docs/service-account-overview) (key file named credentials.json)
- [TBA API key](https://www.thebluealliance.com/apidocs) (key file named X-TBA-Auth-Key)

If you are on our team, reach out to me (Nolan) and I can give you the ones I used :)

--- 

The next step is to change the parameters in Main.kt to specify the google sheet you are writing to and the event id. The google sheet ID is this part of the link:

docs.google.com/spreadsheets/d/**1bi-MqGwfqEBgKGzkezM0M2CW7xJw066YwshSpxw6uM4**/edit#gid=0

The event ID can be found in a similar way on its respective blue alliance page:

thebluealliance.com/event/**2023azgl**

Please note that you must give the service account write permission for the spreadsheet!