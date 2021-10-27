# jda-discord-bot-starter
Get started with our first Discord Bot

# Join our Hackathon Discord
https://discord.gg/JYCUmbMcnW

# How to get started
The start is supper easy. We assume that you already have a working java developer environment like InteliJ IDEA or Eclipse. If not just let us know and we are happy to help you getting ready.

To bring your bot alive you can follow these steps:
- Open [https://discord.com/developers/applications](https://discord.com/developers/applications)
- Create new application (top-right)
- Give your bot a nice name
- Copy `APPLICATION ID` to config (`discord-client-id`)
- Got to subpage "Bot" and click "Add a Bot"
- Copy `TOKEN` to config (`discord-token`)
- Start the bot in your IDE

If everything is successful, you should see in the log that the bot is now connected as well as a link to invite it to a server. If you are at this step you can ask Patrick or Nikola on the Hackathon Discord to invite it to the server.

# Some Challenges to solve
- Add a command and reply to it
- Answer with an image from your resources folder
- Create a complex command with options
- Send messages without a command is used (in a server channel as well as Direct Message)
- Listen for some events and do something with it
- Improve the command handling (rate-limiting is a thing)
- Assign roles and or manage channels *(hint: ask on the Hackathon Discord for some more permissions for your bot)*
- Do some audio stuff *(hint: you need opus-java and search for a solution for the fatJar task)*

# The library we use
We use [JDA](https://github.com/DV8FromTheWorld/JDA) for this project.

# Useful links
- [https://discord.com/developers/applications](https://discord.com/developers/applications)
- [https://discord.com/developers/docs/intro](https://discord.com/developers/docs/intro)
