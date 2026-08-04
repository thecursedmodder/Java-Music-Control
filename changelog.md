1.0.1
-Fixed rare crash from a multithreading issue in Channel
-Made the debug window false by default
-Changed all triggers in the example folder to have TriggerExample after their name.

1.0.2 
-added Minecraft's master volume to the player master volume calculation.
-fixed issue with not being able to load songs from another mod. >:(

1.0.3
-integrated the canSongPlay boolean into the getSong function for advanced users.

--Context Updates--
-added speedIsAtOrBeyond check
-added worldHeight check
-added recentlyDamage check
-added isFalling check
-added isUnderWater check
-added onGround check
-added isClimbing check
-added isSwimming check
-added isDayCountAtOrBeyond check
1.0.4
-switched the loading boolean to atomic from volatile for a major stability increase.

1.0.5
-moved trigger state monitors to the TriggerBase class. Now triggers are automatically added to the debug window
-fixed continue function for songs. If a song is fading out and becomes playable it will fade back in from where it's at.
-fixed triggers with multiple songs crashing when a song ends.
-added isDay context check
-added isFullMoon and isMoonPhase context check.

1.1.0
-reverted back to manual trigger monitor adding (Sucks, but it's not too hard. Will try to fix later)
-fixed issue with audio player crashing due to false trigger replacement checks
-fixed small issues.
-added a delay before a trigger can play again.
--Context Updates--
-split up day and night context into 4
-added dawn, day, dusk, and night checks
-added home check
-added biome check (This might be in the last update idk)`

1.1.1
-added method for enabling debugScreen
-added continue from for songs, and a reset timer for it.
-added isPlaying boolean to Song class.
-added a new fading engine(It's not implemented yet, but it shows potential, having perfect fades with no popping)
-fixed memory leak from audioPlayers not closing correctly
-fixed audio fading-out and not fading-back when tabbing out.
-fixed screen out of focus audio pause (This actually fix a few other reliability issues)
-increased the buffer size for the audio output (this adds more latency to the underwater effect, but makes stutters disappear for the most part)

1.1.2
-fixed small bugs.
-changed logs to have less spam and be more useful for debugging
-removed a line that sets a thread variable null. This might fix some cancellation issues?
-fixed continue from feature

1.2.0
-Changed out the glide function for buttery smooth glides (Confirmed and Test)! (This took far long than I thought it would >:\) 
-Dropped back down to 4096 for hardware audio buffer. It seems to stutter more in certain conditions past that.
-fixed issue with layer's fading in. (Confirmed and Test)

--Trigger Updates--
Enhanced the extendable mob trigger

--Context Updates--
-Added a Tag Biomes check
-Added a isMobTypeNearby
-Added more customizable time check (isBetweenTimeFrame)

This has build has been stable for the 30 minutes of consistent runtime

1.2.2
-attempt to support 1.20.1 - 1.20.5 (This isn't looking likely)
-fixed audio layer issue involving masterVolume not effecting it.

1.2.3
-fixed DataGen crash.
-fixed volume jump bug with songs above or bellow volume 1.0 (Confirmed and Test)
-fixed audio looping when screen is paused

-removed example song.

1.2.4
-updated config description for increased clarity
-Changed name of a subscribe method for clarity (This won't affect compatibility)

1.3.0
added a tempFadeIn and tempFadeOut field to the Song class.
Essentially, you can change these fields to be greater a different value from the base FadeOut/In values.
After the song is finished or replaced it resets these fields. 


