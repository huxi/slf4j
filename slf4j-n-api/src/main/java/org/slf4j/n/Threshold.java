package org.slf4j.n;

/**
 * The threshold of a logger.
 */
public enum Threshold
{
		ALL(Level.TRACE), TRACE(Level.TRACE), DEBUG(Level.DEBUG),
		INFO(Level.INFO), WARN(Level.WARN), ERROR(Level.ERROR), OFF(null);

		private final Level level;

		Threshold(Level level)
		{
				this.level = level;
		}

		public boolean passes(Level level)
		{
				return this.level != null &&
this.level.compareTo(level) <= 0;
		}
}

