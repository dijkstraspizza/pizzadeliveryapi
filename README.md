# Dijkstra's Pizza API
To run this project locally, first download and install MongoDB.
Once Mongo is working on the terminal, set up your editor/project with the environment variable MONGODB_URI=mongodb://localhost.

# To open mongolab from the terminal, inside the folder that is the heroku repo run the following command in project root.
~$heroku addons:open mongolab

# To run Jacoco. Run following command in project root folder, find jacoco index under project-root/target/site/jacoco/index.html then open in browser.
~$mvn jacoco:report

# The following may come in handy if jacoco doesn't seem
# to be working.
~$mvn clean install jacoco:prepare-agent jacoco:report

# =========LOMBOK TESTING EXCLUSION========
# For lombok test exclusion. Models get-
import lombok.Builder;
import lombok.Data;
# then the model classes get annotated with-
@Data
@Builder
# The associated classes with testable logic get-
mport lombok.Data;
import lombok.extern.java.Log;
# ... and annotated with -
@Log
@Data


