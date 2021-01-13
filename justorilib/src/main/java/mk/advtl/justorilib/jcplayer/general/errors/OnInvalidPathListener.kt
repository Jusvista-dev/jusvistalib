package mk.advtl.justorilib.jcplayer.general.errors

import mk.advtl.justorilib.jcplayer.model.JcAudio

/**
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 18/12/17.
 * Jesus loves you.
 */
interface OnInvalidPathListener {

    /**
     * Audio path error jcPlayerManagerListener.
     * @param jcAudio The wrong audio.
     */
    fun onPathError(jcAudio: JcAudio)
}