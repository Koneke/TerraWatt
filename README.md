TerraWatt is/will be a sidescrolling, 2D platformer game in an open world, similar to Terraria. A lot of ideas have been drawn from mod packs for Minecraft, the ones allowing all kinds of crazy machines, as I'm very interested in being able to build your own contraptions and stuff in games.

Currently requires a specific folder structure (although you could change this up if you want to, this is just the way it looks for me).

Folder for the game, side by side with a Guts folder (Koneke/Guts).
The folder should contain the following:
clean, process, compile and run scripts (process is optional, as is the provided go script, which is just to do all the steps quicker and easier)
bin folder for output
lib folder with LWJGL, slick, jinput
native folder for the libs
src folder for the source code
osrc folder for the processed source
res folder for the textures and such
winenv folder to store the script files in windows form (although this is very outdated at the moment, because I don't use windows, you should be able to figure it out without too much troubles)

I usually run this by being in the root folder of the game and running ./go lh.koneke.games.TerraWatt.Game, but there's, of course, other ways of doing this.

Koneke
