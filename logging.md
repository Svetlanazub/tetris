# Logging

Tinylog is used as a current logging library ([link](https://tinylog.org/v2/)).

Config file for logging is present under the following path: [src/main/resources/tinylog.properties](src/main/resources/tinylog.properties)

##Default settings

* Logs to a file under path: `logs/`.
Example name of a log: "2021-06-18_0.log" where 0 is the counter that is increased every time a file exceeds the 
size limit (see below), or a new game is started on the same date.
* Log files are limited in size to 5MB.
* Logs messages with levels FATAL and ERROR (Tinylog levels are ERROR and WARN respectively).
  More detailed levels are: DEBUG and TRACE.
  This can be configured in the [properties](src/main/resources/tinylog.properties) file. 

The app logs its flow and how pieces were moved along with the final score.

The time is not logged as it is not yet present in the master branch.

The tempo (time passed between printing the screens) is logged.

##Note
The small downside of this library is that if we want to use a date as a name of the log file and then limit the 
size of this file, we cannot append to the last log file.
The `writer.append = true` doesn't work for Rolling File Writer, only for File Writer. [docs](https://tinylog.org/v2/configuration/#file-writer)