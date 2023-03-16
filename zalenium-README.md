# Zalenium

## What is it?
A flexible and scalable container based Selenium Grid with video recording, live preview, basic auth & dashboard.

## How to run it ?
### Prerequisites
Docker version >= 1.11.1 .
Make sure that docker info works without errors.

### Pull docker images
Pull docker-selenium : docker pull elgalu/selenium
Pull Zalenium        : docker pull dosel/zalenium

### Run Zalenium
docker run --rm -ti --name zalenium -p 4444:4444 `
      -v /var/run/docker.sock:/var/run/docker.sock `
      -v /tmp/videos:/home/seluser/videos `
      --privileged dosel/zalenium start

☝️ To stop Zalenium use : docker stop zalenium

#### How to run tests ?
Point your WebDriver tests to http://localhost:4444/wd/hub

Example
WebDriver driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), firefoxOptions);