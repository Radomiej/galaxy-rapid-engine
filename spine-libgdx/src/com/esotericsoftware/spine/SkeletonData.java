/******************************************************************************
 * Spine Runtimes Software License v2.5
 *
 * Copyright (c) 2013-2016, Esoteric Software
 * All rights reserved.
 *
 * You are granted a perpetual, non-exclusive, non-sublicensable, and
 * non-transferable license to use, install, execute, and perform the Spine
 * Runtimes software and derivative works solely for personal or internal
 * use. Without the written permission of Esoteric Software (see Section 2 of
 * the Spine Software License Agreement), you may not (a) modify, translate,
 * adapt, or develop new applications using the Spine Runtimes or otherwise
 * create derivative works or improvements of the Spine Runtimes or (b) remove,
 * delete, alter, or obscure any trademarks or any copyright, trademark, patent,
 * or other intellectual property or proprietary rights notices on or in the
 * Software, including any copy thereof. Redistributions in binary or source
 * form must include this license and terms.
 *
 * THIS SOFTWARE IS PROVIDED BY ESOTERIC SOFTWARE "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO
 * EVENT SHALL ESOTERIC SOFTWARE BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES, BUSINESS INTERRUPTION, OR LOSS OF
 * USE, DATA, OR PROFITS) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *****************************************************************************/

package com.esotericsoftware.spine;

import com.badlogic.gdx.utils.Array;

/** Stores the setup pose and all of the stateless data for a skeleton.
 * <p>
 * See <a href="http://esotericsoftware.com/spine-runtime-architecture#Data-objects">Data objects</a> in the Spine Runtimes
 * Guide. */
public class SkeletonData {
	String name;
	final Array<BoneData> bones = new Array(); // Ordered parents first.
	final Array<SlotData> slots = new Array(); // Setup pose draw order.
	final Array<Skin> skins = new Array();
	Skin defaultSkin;
	final Array<EventData> events = new Array();
	final Array<Animation> animations = new Array();
	final Array<IkConstraintData> ikConstraints = new Array();
	final Array<TransformConstraintData> transformConstraints = new Array();
	final Array<PathConstraintData> pathConstraints = new Array();
	float width, height;
	String version, hash;

	// Nonessential.
	float fps = 30;
	String imagesPath;

	// --- Bones.

	public Array<BoneData> getBones () {
		return bones;
	}

	public BoneData findBone (String boneName) {
		if (boneName == null) throw new IllegalArgumentException("boneName cannot be null.");
		Array<BoneData> bones = this.bones;
		for (int i = 0, n = bones.size; i < n; i++) {
			BoneData bone = bones.get(i);
			if (bone.name.equals(boneName)) return bone;
		}
		return null;
	}

	// --- Slots.

	public Array<SlotData> getSlots () {
		return slots;
	}

	public SlotData findSlot (String slotName) {
		if (slotName == null) throw new IllegalArgumentException("slotName cannot be null.");
		Array<SlotData> slots = this.slots;
		for (int i = 0, n = slots.size; i < n; i++) {
			SlotData slot = slots.get(i);
			if (slot.name.equals(slotName)) return slot;
		}
		return null;
	}

	// --- Skins.

	public Skin getDefaultSkin () {
		return defaultSkin;
	}

	public void setDefaultSkin (Skin defaultSkin) {
		this.defaultSkin = defaultSkin;
	}

	public Skin findSkin (String skinName) {
		if (skinName == null) throw new IllegalArgumentException("skinName cannot be null.");
		for (Skin skin : skins)
			if (skin.name.equals(skinName)) return skin;
		return null;
	}

	public Array<Skin> getSkins () {
		return skins;
	}

	// --- Events.

	public EventData findEvent (String eventDataName) {
		if (eventDataName == null) throw new IllegalArgumentException("eventDataName cannot be null.");
		for (EventData eventData : events)
			if (eventData.name.equals(eventDataName)) return eventData;
		return null;
	}

	public Array<EventData> getEvents () {
		return events;
	}

	// --- Animations.

	public Array<Animation> getAnimations () {
		return animations;
	}

	public Animation findAnimation (String animationName) {
		if (animationName == null) throw new IllegalArgumentException("animationName cannot be null.");
		Array<Animation> animations = this.animations;
		for (int i = 0, n = animations.size; i < n; i++) {
			Animation animation = animations.get(i);
			if (animation.name.equals(animationName)) return animation;
		}
		return null;
	}

	// --- IK constraints

	public Array<IkConstraintData> getIkConstraints () {
		return ikConstraints;
	}

	public IkConstraintData findIkConstraint (String constraintName) {
		if (constraintName == null) throw new IllegalArgumentException("constraintName cannot be null.");
		Array<IkConstraintData> ikConstraints = this.ikConstraints;
		for (int i = 0, n = ikConstraints.size; i < n; i++) {
			IkConstraintData constraint = ikConstraints.get(i);
			if (constraint.name.equals(constraintName)) return constraint;
		}
		return null;
	}

	// --- Transform constraints

	public Array<TransformConstraintData> getTransformConstraints () {
		return transformConstraints;
	}

	public TransformConstraintData findTransformConstraint (String constraintName) {
		if (constraintName == null) throw new IllegalArgumentException("constraintName cannot be null.");
		Array<TransformConstraintData> transformConstraints = this.transformConstraints;
		for (int i = 0, n = transformConstraints.size; i < n; i++) {
			TransformConstraintData constraint = transformConstraints.get(i);
			if (constraint.name.equals(constraintName)) return constraint;
		}
		return null;
	}

	// --- Path constraints

	public Array<PathConstraintData> getPathConstraints () {
		return pathConstraints;
	}

	public PathConstraintData findPathConstraint (String constraintName) {
		if (constraintName == null) throw new IllegalArgumentException("constraintName cannot be null.");
		Array<PathConstraintData> pathConstraints = this.pathConstraints;
		for (int i = 0, n = pathConstraints.size; i < n; i++) {
			PathConstraintData constraint = pathConstraints.get(i);
			if (constraint.name.equals(constraintName)) return constraint;
		}
		return null;
	}

	// ---

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public float getWidth () {
		return width;
	}

	public void setWidth (float width) {
		this.width = width;
	}

	public float getHeight () {
		return height;
	}

	public void setHeight (float height) {
		this.height = height;
	}

	public String getVersion () {
		return version;
	}

	public void setVersion (String version) {
		this.version = version;
	}

	public String getHash () {
		return hash;
	}

	public void setHash (String hash) {
		this.hash = hash;
	}

	public String getImagesPath () {
		return imagesPath;
	}

	public void setImagesPath (String imagesPath) {
		this.imagesPath = imagesPath;
	}

	public float getFps () {
		return fps;
	}

	public void setFps (float fps) {
		this.fps = fps;
	}

	public String toString () {
		return name != null ? name : super.toString();
	}
}
