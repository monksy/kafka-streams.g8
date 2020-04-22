A [Giter8][g8] template for kafka streams scala application.

This template uses kafka-streams library from Apache Kafka.

## Running
```bash
sbt new monksy/kafka-streams.g8
```

TODO
----
 * Add healthchecks 
 * Upgrade to Kafka 2.5
 * Add assembly plugin to the build
 * Add in style checks 
 * Add in wort removers 
 * Beef up the default documentation
 * Add Minimal Code coverage to keep things running well
 * Clean up the kafka config section in the app config
 * Add a docker file 
 * Bleed in Kafka settings via the properties file rather than setting them

 
Template license
----------------

Based on the template by [Ogbajie Ikenna](https://twitter.com/idarlington)

To the extent possible under law, the author(s) have dedicated all copyright and related
and neighboring rights to this template to the public domain worldwide.
This template is distributed without any warranty. See <http://creativecommons.org/publicdomain/zero/1.0/>.

[g8]: http://www.foundweekends.org/giter8/
