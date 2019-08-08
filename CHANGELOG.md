# Changelog

All notable changes to this project are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]
### Added
- Error code [FORBIDDEN_IP_ORIGIN](https://ipregistry.co/docs/errors#FORBIDDEN_IP_ORIGIN).
- Carrier class and field.
- Connection domain field.
- Connection type field and enum.
- Security is_cloud_provider field.

### Changed
- Implement equals/hashCode in all IpInfo subfield classes.
- Default cache implementation will expire entries older than 24 hours by default.
- Allow passing any int value to `expireAfter` and `maximumSize` parameters of DefaultCache constructor.

## [2.0.0] - 2019-07-19
### Added
- Changelog file.
- Manifest file with build information on generated JAR.
- Missing file headers.
- Unknown IP type.
- Utility class `IpregistryOptions`.

### Changed
- Rename `EmptyCache` class to `NoCache`.
- Rename `IpData`* classes to `IpInfo`*.
- Rename `Ipregistry` class to `IpregistryClient`.
- Use all lookup parameters (including options) to cache results.

### Removed
- Remove docs/ folder.

### Fixed
- Fix NPE with origin lookup.

## [1.1.0] - 2019-07-03

- Increased version number for testing purposes.

## [1.0.0] - 2019-07-03

- First public release.

[Unreleased]: https://github.com/ipregistry/ipregistry-java/compare/v2.0.0...HEAD
[2.0.0]: https://github.com/ipregistry/ipregistry-java/compare/v1.1.0...v2.0.0
[1.1.0]: https://github.com/ipregistry/ipregistry-java/compare/v1.0.0...v1.1.0
[1.0.0]: https://github.com/ipregistry/ipregistry-java/releases/tag/v1.0.0