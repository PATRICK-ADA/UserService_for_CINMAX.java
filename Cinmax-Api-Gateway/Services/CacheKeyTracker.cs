using Microsoft.Extensions.Caching.Memory;

namespace Cinmax.Api.Gateway.Services;

public class CacheKeyTracker
{
    private readonly HashSet<string> _keys = new();
    private readonly IMemoryCache _cache;

    public CacheKeyTracker(IMemoryCache cache)
    {
        _cache = cache;
    }

    public void AddKey(string key)
    {
        lock (_keys)
        {
            _keys.Add(key);
        }
    }

    public void RemoveKeysByPrefix(string prefix)
    {
        lock (_keys)
        {
            var keysToRemove = _keys.Where(key => key.StartsWith(prefix)).ToList();
            foreach (var key in keysToRemove)
            {
                _cache.Remove(key);
                _keys.Remove(key);
            }
        }
    }

    public void RemoveAllKeys()
    {
        lock (_keys)
        {
            foreach (var key in _keys)
            {
                _cache.Remove(key);
            }
            _keys.Clear();
        }
    }
}