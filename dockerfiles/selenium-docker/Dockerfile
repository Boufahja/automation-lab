FROM ubuntu:18.04
RUN apt-get update && apt-get upgrade

# Essential tools and xvfb
RUN apt-get update && apt-get install -y \
    software-properties-common \
    unzip \
    curl \
    novnc \
    websockify \
    xvfb \
    x11vnc \
    fluxbox \
    nano && \
    rm -rf /var/lib/apt/lists/*

# Chrome browser to run the tests
RUN curl https://dl-ssl.google.com/linux/linux_signing_key.pub -o /tmp/google.pub \
    && cat /tmp/google.pub | apt-key add -; rm /tmp/google.pub \
    && echo 'deb http://dl.google.com/linux/chrome/deb/ stable main' > /etc/apt/sources.list.d/google.list \
    && mkdir -p /usr/share/desktop-directories \
    && apt-get -y update && apt-get install -y google-chrome-stable
# Disable the SUID sandbox so that chrome can launch without being in a privileged container
RUN dpkg-divert --add --rename --divert /opt/google/chrome/google-chrome.real /opt/google/chrome/google-chrome \
    && echo "#!/bin/bash\nexec /opt/google/chrome/google-chrome.real --no-sandbox --disable-setuid-sandbox \"\$@\"" > /opt/google/chrome/google-chrome \
    && chmod 755 /opt/google/chrome/google-chrome

# Chrome Driver
RUN mkdir -p /opt/selenium \
    && curl http://chromedriver.storage.googleapis.com/111.0.5563.64/chromedriver_linux64.zip -o /opt/selenium/chromedriver_linux64.zip \
    && cd /opt/selenium; unzip /opt/selenium/chromedriver_linux64.zip; rm -rf chromedriver_linux64.zip; ln -fs /opt/selenium/chromedriver /usr/local/bin/chromedriver;

# Firefox browser to run the tests 
RUN apt install -y firefox

# Gekko Driver
ENV GECKODRIVER_VERSION 0.32.2
RUN wget --no-verbose -O /tmp/geckodriver.tar.gz https://github.com/mozilla/geckodriver/releases/download/v$GECKODRIVER_VERSION/geckodriver-v$GECKODRIVER_VERSION-linux64.tar.gz \
  && rm -rf /opt/geckodriver \
  && tar -C /opt -zxf /tmp/geckodriver.tar.gz \
  && rm /tmp/geckodriver.tar.gz \
  && mv /opt/geckodriver /opt/geckodriver-$GECKODRIVER_VERSION \
  && chmod 755 /opt/geckodriver-$GECKODRIVER_VERSION \
  && ln -fs /opt/geckodriver-$GECKODRIVER_VERSION /usr/bin/geckodriver \
  && ln -fs /opt/geckodriver-$GECKODRIVER_VERSION /usr/bin/wires

# create workspace folder
RUN mkdir /home/Workspace

# python
RUN add-apt-repository ppa:deadsnakes/ppa
RUN apt-get update && apt-get install -y \
    python3.8 \
    python3-setuptools \
    python3-pip
RUN update-alternatives --install /usr/bin/python3 python3 /usr/bin/python3.8 1 \
    && update-alternatives --config python3

# selenium
RUN pip3 install selenium \
    selenium webdriver-manager \
    robotframework \
    --upgrade robotframework-seleniumlibrary \
    robotframework-requests \
    --upgrade robotframework-appiumlibrary
#-----------------------------------------------------------------------
# Microsoft Edge to run the tests
RUN curl https://packages.microsoft.com/keys/microsoft.asc | gpg --dearmor > microsoft.gpg 
RUN install -o root -g root -m 644 microsoft.gpg /etc/apt/trusted.gpg.d/ 
RUN echo "deb https://packages.microsoft.com/repos/edge stable main" > /etc/apt/sources.list.d/microsoft-edge-dev.list
RUN rm microsoft.gpg
RUN apt update && apt install microsoft-edge-stable

# Install Edge WebDriver
RUN wget -q https://msedgedriver.azureedge.net/111.0.1661.62/edgedriver_linux64.zip -O edgedriver.zip \
    && unzip -q edgedriver.zip \
    && rm edgedriver.zip \
    && chmod +x msedgedriver \
    && mv msedgedriver /usr/local/bin/
#-----------------------------------------------------------------------
EXPOSE 5900 6080
ENV DISPLAY :1

CMD [ "sh", "-c", "Xvfb :1 -screen 0 1024x768x16 & \
                    x11vnc -nopw -xkb -forever -shared & \
                    websockify --web /usr/share/novnc/ 6080 localhost:5900 & \
                    tail -f /dev/null" ]