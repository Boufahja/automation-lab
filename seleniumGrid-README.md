# Selenium Grid 

## What is it?
Selenium Grid is a part of the Selenium Suite that specializes in running multiple tests across different browsers, operating systems, and machines in parallel. It is achieved by routing the commands sent by the client to remote browser instances -known as Nodes- via a server which is the central point where you load your tests into -known as Hub.

## Execution modes
### Standalone
Standalone combines all Grid components seamlessly into one.
#### How to start Docker container ?
Template command
Browser : docker run -d -p HubPort:4444 -p VNCPort:7900 --shm-size="2g" selenium/standalone-browser:version

Examples
Firefox : docker run -d -p 4444:4444 -p 7900:7900 -p 5900:5900 --shm-size="2g" selenium/standalone-firefox:latest
Chrome  : docker run -d -p 4444:4444 -p 7901:7900 --shm-size="2g" selenium/standalone-chrome:latest
Edge    : docker run -d -p 4444:4444 -p 7902:7900 --shm-size="2g" selenium/standalone-edge:latest
#### How to run tests ?
Point your WebDriver tests to http://gridUrl:HubPort

Selenium example
WebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444/").toURL(), firefoxOptions);

#### Access to Selenium Grid User Interface ?
Go to http://localhost:HubPort   
#### How to see what is happening inside the container ?
Go to http://localhost:VNCPort
☝️ password = secret

Example to automatically
http://localhost:7900/?autoconnect=1&resize=scale&password=secret

### Hub and Nodes
Hub and Node is the most used. it runs Hub and Nodes in seperated containers.
#### How to start Docker containers ?
1- Create network grid : docker network create grid
2- Start hub container : docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub:latest
3- Start nodes containers 

Chrome : 
docker run -d -p 7900:7900 --net grid  --name chrome-node -e SE_EVENT_BUS_HOST=selenium-hub `
    --shm-size="2g" `
    -e SE_EVENT_BUS_PUBLISH_PORT=4442 `
    -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 `
    selenium/node-chrome:latest

Edge : 
docker run -d -p 7901:7900 --net grid --name edge-node -e SE_EVENT_BUS_HOST=selenium-hub `
    --shm-size="2g" `
    -e SE_EVENT_BUS_PUBLISH_PORT=4442 `
    -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 `
    selenium/node-edge:latest

Firefox :
docker run -d -p 7902:7900 --net grid --name firefox-node -e SE_EVENT_BUS_HOST=selenium-hub `
    --shm-size="2g" `
    -e SE_EVENT_BUS_PUBLISH_PORT=4442 `
    -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 `
    selenium/node-firefox:latest

☝️ To remove network grid use : docker network rm grid
☝️ Port 7900 in docker containers is reserved for VNC so you must to bind it to different ports when using multiple nodes

#### How to run tests ?
Point your WebDriver tests to http://localhost:HubPort

Example
WebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444/").toURL(), firefoxOptions);